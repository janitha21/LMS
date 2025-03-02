import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode as jwt_decode } from "jwt-decode";
//import ChatBox from "../components/chat_system/ChatBox.jsx";
import "./Dashboard.css";

function Dashboard() {
  const [subjects, setSubjects] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    if (!token) {
      setError("No token found");
      setIsLoading(false);
      navigate("/login");
      return;
    }

    try {
      const decodedToken = jwt_decode(token);
      console.log("User ID:", decodedToken.userID);
      console.log("Email:", decodedToken.userEmail);

      const apiUrl = `http://localhost:8080/subject/get-by-id/${decodedToken.userID}?email=${encodeURIComponent(decodedToken.userEmail)}`;
      console.log(apiUrl);

      fetch(apiUrl)
        .then((response) => {
          if (!response.ok) throw new Error("Failed to fetch subjects");
          return response.json();
        })
        .then((data) => {
          setSubjects(data);
          setIsLoading(false);
        })
        .catch((err) => {
          setError(err.message);
          setIsLoading(false);
        });
    } catch (error) {
      console.error("Invalid token:", error);
      localStorage.removeItem("token");
      navigate("/login");
    }
  }, [navigate]);

  function handleImageError(e) {
    e.target.src = "default-icon.png";
  }

  function handleSubjectClick(subjectId) {
    navigate(`/subjects-content/${subjectId}`);
  }

  function logout() {
    localStorage.removeItem("token");
    navigate("/login");
  }

  return (
    <div>
      
      <div className="p-4">
        <button onClick={logout}>Logout</button>

        <header className="p-3 mb-3 border-bottom">
          <div className="container d-flex flex-wrap align-items-center justify-content-between">
            <ul className="nav">
              <li>
                <a href="#" className="nav-link px-2 link-body-emphasis">Overview</a>
              </li>
              <li>
                <a href="#" className="nav-link px-2 link-body-emphasis">Inventory</a>
              </li>
              <li>
                <a href="#" className="nav-link px-2 link-body-emphasis">Customers</a>
              </li>
              <li>
                <a href="#" className="nav-link px-2 link-body-emphasis">Products</a>
              </li>
            </ul>
            <input type="search" className="form-control w-auto" placeholder="Search..." />
          </div>
        </header>

        {isLoading && <p className="dashboard-loading">Loading subjects...</p>}
        {error && <p className="dashboard-error">{error}</p>}

        {!isLoading && !error && (
          <div className="dashboard-card-container">
            {subjects.map((subject) => (
              <div key={subject.subjectId} className="dashboard-card" onClick={() => handleSubjectClick(subject.subjectId)}>
                <div className="d-flex flex-column p-4 gap-4">
                  <div className="text-center">
                    <img
                      src={subject.subjectIcon}
                      alt={subject.subjectName || "Subject Icon"}
                      className="dashboard-image"
                      onError={handleImageError}
                    />
                  </div>
                  <div className="text-center">
                    <h6 className="dashboard-heading">{subject.subjectName}</h6>
                    <p className="dashboard-description">
                      {subject.subjectDescription || "No description available."}
                    </p>
                  </div>
                  <button className="dashboard-button" onClick={() => handleSubjectClick(subject.subjectId)}>
                    View Details
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default Dashboard;
