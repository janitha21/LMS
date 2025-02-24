import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Spinner, Alert, Card, Button, Container, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function ContentLoad() {
  const [contents, setContents] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const { subjectId } = useParams();
  const navigate = useNavigate(); 

  useEffect(() => {
    fetch(`http://localhost:8080/content/get-by-id?subjectId=${subjectId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch contents");
        }
        return response.json();
      })
      .then((data) => {
        setContents(data);
        setIsLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setIsLoading(false);
      });
  }, [subjectId]);

  function loadTasks(contentId){
    
    navigate(`/load-task/${contentId}`)
  }
  

  return (
   
    
    <Container className="mt-4">
      
      <h2 className="text-center mb-4">📚 Subject Content</h2>

      {/* Loading State */}
      {isLoading && (
        <div className="text-center">
          <Spinner animation="border" role="status" />
          <p>Loading contents...</p>
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
          {contents.map((content) => (
            <Col key={content.contentId} xs={12} md={12} lg={12} className="mb-4">
              <Card className="shadow-sm w-100">
                <Card.Body className="d-flex flex-column">
                  <Card.Title className="text-primary">{content.title}</Card.Title>
                  <Card.Text> {content.description}</Card.Text>
                  <div className="mt-auto">
                    <Button variant="outline-primary" onClick={() => loadTasks(content.contentId)}>View Details</Button>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      )}
    </Container>
  
  );
}

export default ContentLoad;
