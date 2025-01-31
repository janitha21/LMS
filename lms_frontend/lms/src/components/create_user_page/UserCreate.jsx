import { useState } from "react";

const UserCreate = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    id: "",
    contact: "",
    email: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevents default form submission behavior
    console.log("Form Submitted", formData);

    const token = localStorage.getItem("token");
    if (token) {
      try {
        const response = await fetch("http://localhost:8080/student/save", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        });

        if (response.ok) {
          alert("Data saved successfully!");
          
        } else {
          alert("Failed to save data.");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    }
  };

  return (
    <div className="container py-5">
      <div className="text-center">
        <h2>Add Student</h2>
        <p className="lead">
          Below is an example form built entirely with Bootstrap’s form controls.
        </p>
      </div>

      <div className="row g-5">
        <div className="col-md-7 col-lg-8">
          <form onSubmit={handleSubmit}>
            <div className="row g-3">
              <div className="col-12">
                <label className="form-label">First name</label>
                <input
                  type="text"
                  className="form-control"
                  name="firstName"
                  value={formData.firstName}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="col-12">
                <label className="form-label">Email</label>
                <input
                  type="email"
                  className="form-control"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                />
              </div>

              <div className="col-12">
                <label className="form-label">Identity number</label>
                <input
                  type="text"
                  className="form-control"
                  name="id"
                  value={formData.id}
                  onChange={handleChange}
                />
              </div>

              <div className="col-12">
                <label className="form-label">Contact Number</label>
                <input
                  type="text"
                  className="form-control"
                  name="contact"
                  value={formData.contact}
                  onChange={handleChange}
                />
              </div>
            </div>

            <hr className="my-4" />

            <button className="w-100 btn btn-primary btn-lg" type="submit">
              Add Student
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default UserCreate;
