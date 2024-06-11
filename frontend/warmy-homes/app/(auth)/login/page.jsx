"use client"
import { useState } from "react";
import styles from "./login-page.module.scss";
import Link from "next/link";

export default function LoginPage() {
  const [state, setState] = useState({
    message: null,
    errors: {},
    loading: false,
    formData: {
      username: "",
      password: "",
    },
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setState((prevState) => ({
      ...prevState,
      formData: {
        ...prevState.formData,
        [name]: value,
      },
      errors: {
        ...prevState.errors,
        [name]: null, // Clear the specific field error on change
      },
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setState((prevState) => ({ ...prevState, loading: true, message: null }));

    try {
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(state.formData),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Something went wrong');
      }

      // Handle successful login here
      console.log("Login successful:", data);
      // Redirect to another page or set authentication state
    } catch (err) {
      console.error("Login failed:", err);
      setState((prevState) => ({
        ...prevState,
        errors: {
          ...prevState.errors,
          commonError: err.message,
        },
      }));
    } finally {
      setState((prevState) => ({ ...prevState, loading: false }));
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.banner}><h1>Login</h1></div>
      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.inputGroup}>
          <label htmlFor="username" className={styles.label}>
            Username
          </label>
          <input
            autoComplete="username"
            className={styles.input}
            id="username"
            name="username"
            placeholder="Enter your username..."
            type="text"
            value={state.formData.username}
            onChange={handleChange}
          />
          {state?.errors?.username && (
            <p className={styles.error}>{state?.errors?.username}</p>
          )}
        </div>
        <div className={styles.inputGroup}>
          <label htmlFor="password" className={styles.label}>
            Password
          </label>
          <input
            autoComplete="current-password"
            className={styles.input}
            id="password"
            name="password"
            placeholder="•••••••••"
            type="password"
            value={state.formData.password}
            onChange={handleChange}
          />
          {state?.errors?.password && (
            <p className={styles.error}>{state?.errors?.password}</p>
          )}
        </div>
        {state?.errors?.commonError && (
          <p className={styles.error}>{state?.errors?.commonError}</p>
        )}
        <div className={styles.forgotPassword}>
          <Link href="/forgot-password">Forgot password?</Link>
        </div>
        <button type="submit" className={styles.submitButton} disabled={state.loading}>
          {state.loading ? "Signing In..." : "LOGIN"}
        </button>
      </form>
      <p className={styles.message}>
        If you don't have an account, <Link href="/register" className={styles.registerLink}>Register now!</Link>
      </p>
    </div>
  );
}
