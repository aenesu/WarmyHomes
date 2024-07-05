"use client"
import { useState } from "react";
import styles from "./login-page.module.scss";
import Link from "next/link";
import Banner from "@/components/common/banner/banner";

export default function LoginPage() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
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
    // Add form submission logic here
    console.log("Form submitted:", formData);
  };

  return (
    <div className={styles.container}>
      <Banner title="LOGIN" />
      <form onSubmit={handleSubmit} className={styles.form}>
        {[
          { id: "username", label: "Username", type: "text" },
          { id: "password", label: "Password", type: "password" }
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
        <Link href="/profile/password/forgot" className={styles.p}>Forgot password?</Link>
        <button type="submit" className={styles.submitButton}>
          LOGIN
        </button>
      </form>
      <p className={styles.message}>
        If you don't have an account, <Link href="/register" className={styles.loginLink}>Register now!</Link>
      </p>
    </div>
  );
}
