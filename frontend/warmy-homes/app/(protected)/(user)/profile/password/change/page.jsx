"use client"
import { useState } from "react";
import styles from "./change-password.module.scss";

export default function ChangePasswordPage() {
  const [formData, setFormData] = useState({
    currentPassword: "",
    newPassword: "",
    retryNewPassword: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your form submission logic here
    console.log("Password change data:", formData);
  };

  return (
    <div className={styles.container}>
      <div className={styles.banner}><h1>Change Password</h1></div>
      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.inputField}>
          <input
            type="password"
            id="currentPassword"
            name="currentPassword"
            value={formData.currentPassword}
            onChange={handleChange}
            required
          />
          <label htmlFor="currentPassword">Current Password</label>
        </div>
        <div className={styles.inputField}>
          <input
            type="password"
            id="newPassword"
            name="newPassword"
            value={formData.newPassword}
            onChange={handleChange}
            required
          />
          <label htmlFor="newPassword">New Password</label>
        </div>
        <div className={styles.inputField}>
          <input
            type="password"
            id="retryNewPassword"
            name="retryNewPassword"
            value={formData.retryNewPassword}
            onChange={handleChange}
            required
          />
          <label htmlFor="retryNewPassword">Retry New Password</label>
        </div>
        {formData.newPassword !== formData.retryNewPassword && <div className={styles.error}>Passwords do not match</div>}
        <button type="submit" className={styles.submitButton}>
          Update
        </button>
      </form>
    </div>
  );
}
