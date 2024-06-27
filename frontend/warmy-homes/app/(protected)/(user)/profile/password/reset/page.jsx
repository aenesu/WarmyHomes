"use client"
import { useState } from "react";
import styles from "./reset-password.module.scss";

export default function ResetPasswordPage() {
  const [formData, setFormData] = useState({
    resetCode: "",
    newPassword: "",
    retryNewPassword: "",
  });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (formData.newPassword !== formData.retryNewPassword) {
      setError("Passwords do not match");
      return;
    }
    setError("");
    // Add your form submission logic here
    console.log("Password reset data:", formData);
  };

  return (
    <div className={styles.container}>
      <div className={styles.banner}><h1>Reset Password</h1></div>
      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.inputField}>
          <input
            type="text"
            id="resetCode"
            name="resetCode"
            className={styles.input}
            value={formData.resetCode}
            onChange={handleChange}
            required
          />
          <label htmlFor="resetCode" className={styles.label}>Reset Code</label>
        </div>
        <div className={styles.inputField}>
          <input
            type="password"
            id="newPassword"
            name="newPassword"
            className={styles.input}
            value={formData.newPassword}
            onChange={handleChange}
            required
          />
          <label htmlFor="newPassword" className={styles.label}>New Password</label>
        </div>
        <div className={styles.inputField}>
          <input
            type="password"
            id="retryNewPassword"
            name="retryNewPassword"
            className={styles.input}
            value={formData.retryNewPassword}
            onChange={handleChange}
            required
          />
          <label htmlFor="retryNewPassword" className={styles.label}>Retry New Password</label>
        </div>
        {error && <div className={styles.error}>{error}</div>}
        <button type="submit" className={styles.submitButton}>
          Reset Password
        </button>
      </form>
    </div>
  );
}
