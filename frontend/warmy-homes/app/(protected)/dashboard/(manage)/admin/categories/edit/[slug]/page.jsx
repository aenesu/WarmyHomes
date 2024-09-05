"use client";
import { useState } from 'react';
import styles from './edit.module.scss';

export default function CategoryEdit() {
  const [status, setStatus] = useState('Active');

  const toggleStatus = () => {
    setStatus(prevStatus => (prevStatus === 'Active' ? 'Deactive' : 'Active'));
  };

  return (
    <div className={styles.container}>
      <form className={styles.form}>
        <div className={styles.inputField}>
          <label>Title</label>
          <input type="text" required />
        </div>

        <div className={styles.inputField}>
          <label>Slug</label>
          <input type="text" required />
        </div>

        <div className={styles.formRow}>
          <div className={`${styles.inputField} ${styles.small}`}>
            <label>Icon</label>
            <input type="text" required />
          </div>

          <div className={`${styles.inputField} ${styles.small}`}>
            <label>Sequence</label>
            <input type="text" required />
          </div>

          <div className={styles.statusContainer}>
            <label>Status</label>
            <button type="button" className={styles.statusButton} onClick={toggleStatus}>
              <span>{status}</span>
              <div className={`${styles.statusIndicator} ${status === 'Active' ? styles.active : ''}`}></div>
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}
