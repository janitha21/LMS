import { useState, useEffect } from "react";

const AddSubject = () => {
  const [options, setOptions] = useState([]); // Store fetched subjects
  const [selected, setSelected] = useState([]); // Store selected subjects
  const [studentEmail, setStudentEmail] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/subject/get-by-id/2") // Change to your API
      .then((response) => response.json())
      .then((data) => setOptions(data))
      .catch((error) => console.error("Error fetching subjects:", error));
  }, []);

  // Handle checkbox change
  function handleChange(event, subjectName) {
    const checked = event.target.checked; // Check if the checkbox is checked or not

    setSelected((prevSelected) =>
      checked
        ? [...prevSelected, subjectName] // Add if checked
        : prevSelected.filter((subject) => subject !== subjectName) // Remove if unchecked
    );
  }
 
  // Show selected subjects
  function show() {
    console.log(selected);
    console.log(options);
  }
  function setEmail(event) {
    setStudentEmail(event.target.value)
    console.log(studentEmail);
    

    
  }

  return (
    <div>
      {options.map((subject) => (
        <div key={subject.subjectId}>
          <label className="list-group-item d-flex gap-2">
            <input
              className="form-check-input flex-shrink-0"
              type="checkbox"
              onChange={(e) => handleChange(e, subject.subjectName)} // Pass subjectName to the handler
            />
            <span>
              {subject.subjectName}
              <small className="d-block text-body-secondary">
                And we end with another snippet of text
              </small>
            </span>
          </label>
        </div>
      ))}

<div className="col-sm-6">
  <label htmlFor="studentEmail"  className="form-label">
    First name
  </label>
  <input
    type="text"
    className="form-control"
    id="firstName"
    placeholder=""
    value={studentEmail}
    required
    onChange={setEmail}
  />

</div>





          
      <button onClick={show}>Click Me</button>
    </div>
  );
};

export default AddSubject;
