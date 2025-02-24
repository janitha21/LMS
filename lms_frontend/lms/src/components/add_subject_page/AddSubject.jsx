import { useState, useEffect } from "react";

const AddSubject = () => {
  const [options, setOptions] = useState([]); // Store fetched subjects
  const [selected, setSelected] = useState([]); // Store selected subjects
  const [studentEmail, setStudentEmail] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/subject/get-all") 
      .then((response) => response.json())
      .then((data) => setOptions(data))
      .catch((error) => console.error("Error fetching subjects:", error));
  }, []);

  // Handle checkbox change
  function handleChange(event, subjectId) {
    const checked = event.target.checked; 

    setSelected((prevSelected) =>
      checked
        ? [...prevSelected, subjectId] // Add if checked
        : prevSelected.filter((subject) => subject !== subjectId) // Remove if unchecked
    );
    
    
  }
 

  async function show() {
    console.log(selected);
   // console.log(options);
     
    try {
      const response = await fetch(`http://localhost:8080/subject/add?studentEmail=${studentEmail}
`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(selected),
      });

      if (response.ok) {
        alert("Data saved successfully!");
        
      } else {
        alert("Failed to save data.");
      }
    } catch (error) {
      console.log();
      
    }
 
 
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
              onChange={(e) => handleChange(e, subject.subjectId)} 
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
