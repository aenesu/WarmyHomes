"use client";
import { useState } from "react";
import styles from "./registerPage.module.scss";
import Link from "next/link";

export default function RegisterPage() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    username: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your form submission logic here
    console.log("Form submitted:", formData);
  };

  return (
    <div className={styles.container}>
      <div className={styles.banner}><h1>Register</h1></div>
      <form onSubmit={handleSubmit} className={styles.form}>
        {[
          { id: "firstName", label: "First Name", type: "text" },
          { id: "lastName", label: "Last Name", type: "text" },
          { id: "username", label: "Username", type: "text" },
          { id: "email", label: "Email", type: "email" },
          { id: "phone", label: "Phone Number", type: "tel" },
          { id: "password", label: "Password", type: "password" },
          { id: "confirmPassword", label: "Confirm Password", type: "password" },
        ].map((field) => (
          <div key={field.id} className={styles.inputField}>
            <input
              type={field.type}
              id={field.id}
              name={field.id}
              value={formData[field.id]}
              onChange={handleChange}
              required
            />
            <label htmlFor={field.id}>{field.label}</label>
          </div>
        ))}
        <button type="submit" className={styles.submitButton}>
          REGISTER
        </button>
      </form>
      <p className={styles.message}>
        If you already have an account, <Link href="/login" className={styles.loginLink}>Login now!</Link>
      </p>
    </div>
  );
}