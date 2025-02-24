import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; 
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import Dashboard from './components/Dashboard';
import SubjectDetail from './components/SubjectDetail'; 
import LogPage from "./components/logPage/LogPage";
import Sample from "./components/sample";
import UserCreate from "./components/create_user_page/UserCreate";
import AddSubject from "./components/add_subject_page/AddSubject";
import ContentLoad from "./components/content_load_page/ContentLoad";
import TaskLoad from "./components/task_load_page/TaskLoad";
import ProtectedRoute from "./components/protectedRouter/ProtectedRoute";

function App() {
  return (
    <Router>
      <header className="header">
        <div className="overlay"></div>
        <div className="content">
          <h1>The Open University of Sri Lanka</h1>
          <p>Empowering minds through knowledge and education</p>
        </div>
      </header>

      <main>
        <Routes>
          
          <Route path="/subject" element={<SubjectDetail />} />
          <Route path="/log" element={<LogPage />} />
          <Route path="/sample" element={<Sample />} />
          <Route path="/user-create" element={<UserCreate />} />
          <Route path="/user-subjects" element={<AddSubject />} />
          <Route path="dashboard/subjects-content/:subjectId" element={<ContentLoad />} />
          <Route path="/load-task/:contentId" element={<TaskLoad />} />

          <Route
            path="/dashboard"
            element={
              <ProtectedRoute>
                <Dashboard />
              </ProtectedRoute>
              
            }
          />

        </Routes>
      </main>

      <footer className="py-5" style={{ backgroundColor: "#212529", color: "#ffffff" , height: "300px"}}>
  <div className="row">
    {/* Section Links */}
    <div className="col-6 col-md-2 mb-3">
      <h5>Section</h5>
      <ul className="nav flex-column">
        <li className="nav-item mb-2">
          <a href="#" className="nav-link p-0" style={{ color: "#f8f9fa" }}>Home</a>
        </li>
        <li className="nav-item mb-2">
          <a href="#" className="nav-link p-0" style={{ color: "#f8f9fa" }}>Features</a>
        </li>
       
       
        <li className="nav-item mb-2">
          <a href="#" className="nav-link p-0" style={{ color: "#f8f9fa" }}>About</a>
        </li>
      </ul>
    </div>

    {/* Newsletter Section */}
    <div className="col-md-5 offset-md-1 mb-3">
      <form>
        <h5>Subscribe to our newsletter</h5>
        <p>Monthly digest of new and exciting from us.</p>
        <div className="d-flex flex-column flex-sm-row w-100 gap-2">
          <label htmlFor="newsletter1" className="visually-hidden">Email address</label>
          <input id="newsletter1" type="email" className="form-control" placeholder="Email address" />
          <button className="btn btn-primary" type="button">Subscribe</button>
        </div>
      </form>
    </div>
  </div>

  {/* Social Links */}
  <div className="d-flex flex-column flex-sm-row justify-content-between py-4 my-4 border-top" style={{ color: "#f8f9fa" }}>
    <p>&copy; 2024 Company, Inc. All rights reserved.</p>
    <ul className="list-unstyled d-flex">
      <li className="ms-3">
        <a className="link-body-emphasis" href="#">
          <svg className="bi" width="24" height="24">
            <use xlinkHref="#twitter" />
          </svg>
        </a>
      </li>
      <li className="ms-3">
        <a className="link-body-emphasis" href="#">
          <svg className="bi" width="24" height="24">
            <use xlinkHref="#instagram" />
          </svg>
        </a>
      </li>
      <li className="ms-3">
        <a className="link-body-emphasis" href="#">
          <svg className="bi" width="24" height="24">
            <use xlinkHref="#facebook" />
          </svg>
        </a>
      </li>
    </ul>
  </div>
</footer>


    </Router>
  );
}

export default App;
