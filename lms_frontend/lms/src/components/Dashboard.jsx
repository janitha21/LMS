import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";  // Import the external CSS file
import { jwtDecode as jwt_decode } from 'jwt-decode';


function Dashboard() {
  const [subjects, setSubjects] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const token = localStorage.getItem("token");
  const navigate = useNavigate(); // Initialize navigate
  const decoded = jwt_decode(token)

  useEffect(function () {
    if (token) {
      try {
        const decodedToken = jwt_decode(token);
        console.log(decodedToken.userID);


        fetch(`http://localhost:8080/subject/get-by-id/${decodedToken.userID}`)
          .then(function (response) {
            if (!response.ok) {
              throw new Error("Failed to fetch subjects");
            }
            return response.json();
          })
          .then(function (data) {
            setSubjects(data);
            setIsLoading(false);
          })
          .catch(function (err) {
            setError(err.message);
            setIsLoading(false);
          });
      } catch {
        console.log("error");
      }
    } else {
      setError("No token found");
      setIsLoading(false);
    }
  }, [token]); // Only run useEffect if token changes

  // Handle image loading error
  function handleImageError(e) {
    e.target.src = "default-icon.png"; // Fallback image
  }

  // Navigate to subject detail page when clicked
  function handleSubjectClick(subjectId) {
    navigate("/subject/" + subjectId); // Navigate to the SubjectDetail route
  }

  return (








    <div className="p-4">

      <div className="b-example-divider"></div>

      <div className="p-4">
        <div className="b-example-divider"></div>

        <header className="p-3 mb-3 border-bottom">
          <div className="container">
            <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
              <a
                href="/"
                className="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none"
              >
                <svg
                  className="bi me-2"
                  width="40"
                  height="32"
                  role="img"
                  aria-label="Bootstrap"
                >
                  <use xlinkHref="#bootstrap" />
                </svg>
              </a>

              <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                {decoded.userID < 10 && (
                  <li>
                    <a href="#" className="nav-link px-2 link-secondary">
                      Overview
                    </a>
                  </li>
                )}
                <li>
                  <a href="#" className="nav-link px-2 link-body-emphasis">
                    Inventory
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link px-2 link-body-emphasis">
                    Customers
                  </a>
                </li>
                <li>
                  <a href="#" className="nav-link px-2 link-body-emphasis">
                    Products
                  </a>
                </li>
              </ul>

              <form
                className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3"
                role="search"
              >
                <input
                  type="search"
                  className="form-control"
                  placeholder="Search..."
                  aria-label="Search"
                />
              </form>

              <div className="dropdown text-end">
                <a
                  href="#"
                  className="d-block link-body-emphasis text-decoration-none dropdown-toggle"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <img
                    src="https://github.com/mdo.png"
                    alt="mdo"
                    width="32"
                    height="32"
                    className="rounded-circle"
                  />
                </a>
                <ul className="dropdown-menu text-small">
                  <li>
                    <a className="dropdown-item" href="#">
                      New project...
                    </a>
                  </li>
                  <li>
                    <a className="dropdown-item" href="#">
                      Settings
                    </a>
                  </li>
                  <li>
                    <a className="dropdown-item" href="#">
                      Profile
                    </a>
                  </li>
                  <li>
                    <hr className="dropdown-divider" />
                  </li>
                  <li>
                    <a className="dropdown-item" href="#">
                      Sign out
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </header>
      </div>
     
     
     

      {isLoading && <p className="dashboard-loading">Loading subjects...</p>}
      {error && <p className="dashboard-error">{error}</p>}

      {!isLoading && !error && (
        <div className="dashboard-card-container">
          {subjects.map(function (subject) {

            return (
              <div
                key={subject.subjectId}
                className="dashboard-card"
                onClick={function () {
                  handleSubjectClick(subject.subjectId);
                }}
              >
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
                  <div className="d-flex justify-center mt-3">
                    <button
                      className="dashboard-button"
                      onClick={() => handleSubjectClick(subject.subjectId)}
                    >
                      View Details
                    </button>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
}

export default Dashboard;
