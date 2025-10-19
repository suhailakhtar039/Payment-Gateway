import React, { useState } from 'react';
import { Link } from 'react-router-dom'; // Import Link

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  // Handle input change
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  // Handle form submit
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Login data:', formData);
    // Later send this to backend with axios/fetch
  };

  return (
    <div className="container mt-5" style={{ maxWidth: '400px' }}>
      <h2 className="text-center mb-4">Login</h2>

      <form onSubmit={handleSubmit}>
        {/* Email Field */}
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email address</label>
          <input
            type="email"
            className="form-control"
            id="email"
            name="email"
            placeholder="Enter your email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>

        {/* Password Field */}
        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password</label>
          <input
            type="password"
            className="form-control"
            id="password"
            name="password"
            placeholder="Enter your password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>

        {/* Submit Button */}
        <div className="d-grid mb-3">
          <button type="submit" className="btn btn-primary">Login</button>
        </div>

        {/* Register Link */}
        <p className="text-center">
          Not registered yet?{' '}
          <Link to="/register" className="text-primary">
            Register now
          </Link>
        </p>
      </form>
    </div>
  );
};

export default Login;
