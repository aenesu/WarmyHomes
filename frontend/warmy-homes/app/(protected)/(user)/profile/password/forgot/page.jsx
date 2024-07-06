"use client"
import { useState } from "react";
import styles from "./forgot-password.module.scss";
import Banner from "@/components/common/banner/banner";


export default function ForgotPasswordPage() {
  const [email, setEmail] = useState("");

  const handleChange = (e) => {
    setEmail(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your form submission logic here
    console.log("Reset code sent to:", email);
  };

  return (
    <div className={styles.container}>
      <Banner title="FORGOT PASSWORD" />
      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.inputField}>
          <input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={handleChange}
            required
          />
          <label htmlFor="email">Email</label>
        </div>
        <button type="submit" className={styles.submitButton}>
          SEND RESET CODE
        </button>
      </form>
    </div>
  );
}
