import  { useState } from "react";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import { useNavigate } from "react-router-dom";


function Sample() {
  const [uploadedFiles, setUploadedFiles] = useState([]);
  const [isDragging, setIsDragging] = useState(false);

  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

 

  // Logout function
  function handleLogout() {
    localStorage.removeItem("token"); // Remove token
    navigate("/login"); // Redirect to login page
  }

  // Handle file selection
  const handleFiles = (files) => {
    const fileArray = Array.from(files);
    setUploadedFiles((prevFiles) => [...prevFiles, ...fileArray]);
  };

  // Handle drag events
  const handleDragOver = (event) => {
    event.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = () => {
    setIsDragging(false);
  };

  const handleDrop = (event) => {
    event.preventDefault();
    setIsDragging(false);

    if (event.dataTransfer.files) {
      handleFiles(event.dataTransfer.files);
    }
  };

  // Handle file input selection
  const handleFileInputChange = (event) => {
    if (event.target.files) {
      handleFiles(event.target.files);
    }

 
  };

  const uploadFile = () =>{
        console.log("work done");
        
  };

  return (
    <>
    <div className="dropdown">
      <button
        className="btn btn-primary dropdown-toggle"
        type="button"
        
        data-bs-toggle="dropdown"
        
      >
        User Menu
      </button>
      <ul className={`dropdown-menu }`}>
        <li>
          <a className="dropdown-item" href="#">
            User Details
          </a>
        </li>
        <li>
          <a className="dropdown-item" href="#">
            Grades
          </a>
        </li>
        <li>
          <hr className="dropdown-divider" />
        </li>
        <li>
          <a className="dropdown-item" href="#" onClick={() => alert("Logged out")}>
            Logout
          </a>
        </li>
      </ul>
    </div>



    <div className="p-4">
      <h1 className="text-center mb-4">File Upload Dropbox</h1>

      {/* Drop Zone */}
      <div
        className={`dropbox ${
          isDragging ? "dragging" : ""
        } p-4 text-center border rounded`}
        onDragOver={handleDragOver}
        onDragLeave={handleDragLeave}
        onDrop={handleDrop}
      >
        <p className="mb-0">Drag & drop files here or click to upload</p>
        <input
          type="file"
          multiple
          className="file-input"
          onChange={handleFileInputChange}
        />
      </div>

      {/* File Preview */}
      {uploadedFiles.length > 0 && (
        <div className="uploaded-files mt-4">
          <h4>Uploaded Files:</h4>
          <ul className="list-group">
            {uploadedFiles.map((file, index) => (
              <li key={index} className="list-group-item">
                {file.name}
              </li>
              
            ))}
            <button className="btn btn-outline-primary" onClick={uploadFile}>
                Submit
            </button>
          </ul>
        </div>
      )}
    </div>
      <div className="container p-4">
      <header className="d-flex justify-content-between align-items-center">
        <h2>Dashboard</h2>

        {/* Dropdown (User Menu) */}
        <div className="dropdown">
          <button
            className="btn btn-primary dropdown-toggle"
            type="button"
            onClick={() => setIsOpen(!isOpen)}
            data-bs-toggle="dropdown"
            aria-expanded={isOpen}
          >
            User Menu
          </button>
          <ul className={`dropdown-menu ${isOpen ? "show" : ""}`}>
            <li>
              <a className="dropdown-item" href="#">
                User Details
              </a>
            </li>
            <li>
              <a className="dropdown-item" href="#">
                Grades
              </a>
            </li>
            <li>
              <hr className="dropdown-divider" />
            </li>
            <li>
              <a className="dropdown-item" href="#" onClick={handleLogout}>
                Logout
              </a>
            </li>
          </ul>
        </div>
      </header>

      <div className="mt-4">
        <p>Welcome to your dashboard!</p>
      </div>
    </div>
    </>
  );
}

export default Sample;
