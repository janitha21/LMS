import  { useState } from "react";

function Sample() {
  const [uploadedFiles, setUploadedFiles] = useState([]);
  const [isDragging, setIsDragging] = useState(false);

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
  );
}

export default Sample;
