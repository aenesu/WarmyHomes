"use client";
import { useState } from "react";
import styles from "./profile-page.module.scss";
import Link from "next/link";

export default function ProfilePage() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    // Add your form update logic here
    console.log("Profile updated:", formData);
  };

  const handleDelete = () => {
    // Add your delete account logic here
    console.log("Account deletion initiated");
  };

  return (
    <div className={styles.container}>
      <div className={styles.banner}><h1>Profile</h1></div>
      <form onSubmit={handleUpdate} className={styles.form}>
        {[
          { id: "firstName", label: "First Name", type: "text" },
          { id: "lastName", label: "Last Name", type: "text" },
          { id: "email", label: "Email", type: "email" },
          { id: "phone", label: "Phone Number", type: "tel" },
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
        <button type="submit" className={styles.updateButton}>
          UPDATE
        </button>
      </form>
      <div className={styles.deleteSection}>
        <button onClick={handleDelete} className={styles.deleteButton}>
        If you want to delete your account, click here! if you delete your account, all related records with this account will also be deleted permanently
        </button>
      </div>
    </div>
  );
}
