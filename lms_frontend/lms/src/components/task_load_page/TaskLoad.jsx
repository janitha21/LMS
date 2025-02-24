import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Spinner, Alert, Card, Container, Row, Col, Button, Form } from "react-bootstrap";
import { format } from "date-fns";

function TaskLoad() {
  const [tasks, setTasks] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const { contentId } = useParams();

  useEffect(() => {
    fetch(`http://localhost:8080/task/get?contentId=${contentId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch contents");
        }
        return response.json();
      })
      .then((data) => {
        setTasks(data);
        setIsLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setIsLoading(false);
      });
  }, [contentId]);


  const renderTaskContent = (task) => {
   
    switch (task.taskType) {
      case "Assignment":
        return (
          <div>
            <h5>Upload Assignment</h5>

            <div>
              <h6></h6>
            </div>
            <Form>
              <Form.Group controlId="formFile" className="mb-3">
                <Form.Label>Upload your assignment file</Form.Label>
                <Form.Control type="file" />
              </Form.Group>
              <Button variant="primary" type="submit">
                Submit
              </Button>
            </Form>

            <div>
              <h6>Added - {format(new Date(task.taskAddedDate), "yyyy-MM-dd HH:mm:ss")}</h6>
              <h6>expired - {format(new Date(task.dueDate), "yyyy-MM-dd HH:mm:ss")}</h6>

            </div>
          </div>
        );
      case "selection":
        return (
          <div>
            <h5>Select Date</h5>
            <Form>
              <Form.Group controlId="formDate" className="mb-3">
                <Form.Label>Select a date</Form.Label>
                <Form.Control type="date" />
              </Form.Group>
              <Button variant="primary" type="submit">
                Submit
              </Button>
            </Form>
          </div>
        );
      default:
        return (
          
          <div>
            <div><p>also showw</p></div>
            <a href={task.link} target="_blank" rel="noopener noreferrer">
              {task.link}
            </a>
            <Card.Text>{task.linkDescription}</Card.Text>
          </div>
        );
    }
  };

  return (
    <Container className="mt-4">
      {/* Loading State */}
      {isLoading && (
        <div className="text-center">
          <Spinner animation="border" role="status" />
          <p>Loading Tasks...</p>
        </div>
      )}

      {/* Error State */}
      {error && (
        <Alert variant="danger" className="text-center">
          {error}
        </Alert>
      )}

      {/* Display Contents */}
      {!isLoading && !error && (
        <Row className="justify-content-center">
          {tasks.map((task) => (
            <Col key={task.taskId} xs={12} md={12} lg={12} className="mb-4">
              <Card className="shadow-sm w-100">
                <Card.Body className="d-flex flex-column">
                  {renderTaskContent(task)}
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      )}
    </Container>
  );
}

export default TaskLoad;