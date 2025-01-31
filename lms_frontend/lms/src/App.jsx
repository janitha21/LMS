import { BrowserRouter as Router, Routes, Route } from "react-router-dom"; // Import BrowserRouter, Routes, and Route
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import Dashboard from './components/Dashboard';
import SubjectDetail from './components/SubjectDetail'; // Ensure this is the correct path for SubjectDetail
import LogPage from "./components/logPage/LogPage";
import Sample from "./components/sample";
import UserCreate from "./components/create_user_page/UserCreate";
import AddSubject from "./components/add_subject_page/AddSubject";

function App() {
  return (
    <>
      <h1>The Open University Of Sri Lanka</h1>
      
      <Router>
        <Routes>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/subject" element={<SubjectDetail />} />
          <Route path="/log" element={<LogPage />} />
          <Route path="/sample" element={<Sample />} />
          <Route path="/user-create" element={<UserCreate />} />
          <Route path="/user-subjects" element={<AddSubject />} />

        </Routes>
      </Router>
    </>
  );
}

export default App;
