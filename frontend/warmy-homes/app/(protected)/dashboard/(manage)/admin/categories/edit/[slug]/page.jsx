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
        {/* Title and Slug */}
        <div className={styles.formRowWithProperties}>
          <div className={`${styles.inputField} ${styles.leftAligned}`}>
            <label>Title</label>
            <input type="text" required />
          </div>

          <div className={`${styles.inputField} ${styles.leftAligned}`}>
            <label>Slug</label>
            <input type="text" required />
          </div>

          {/* Property Container */}
          <div className={styles.propertyContainer}>
            <h3 className={styles.propertyTitle}>Properties</h3>
            <div className={styles.addPropertyBox}></div>
            <div className={styles.propertyItem}>
              <span className={styles.propertyName}>Bedroom</span>
              <div className={styles.propertyActions}>
                {/* Edit and Delete Buttons */}
                <svg className={styles.editIcon} viewBox="0 0 24 24">
                  <path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z" />
                </svg>
                <svg className={styles.deleteIcon} viewBox="0 0 24 24">
                  <path d="M16 9v10H8V9h8m-1.5-6h-5l-1 1H5v2h14V4h-3.5l-1-1z" />
                </svg>
              </div>
            </div>
            <div className={styles.propertyItem}>
              <span className={styles.propertyName}>Bathroom</span>
              <div className={styles.propertyActions}>
                {/* Edit and Delete Buttons */}
                <svg className={styles.editIcon} viewBox="0 0 24 24">
                  <path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z" />
                </svg>
                <svg className={styles.deleteIcon} viewBox="0 0 24 24">
                  <path d="M16 9v10H8V9h8m-1.5-6h-5l-1 1H5v2h14V4h-3.5l-1-1z" />
                </svg>
              </div>
            </div>
          </div>
        </div>

        {/* Icon, Sequence, and Status */}
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

        {/* Button Group */}
        <div className={styles.buttonGroup}>
          <button type="button" className={styles.deleteButton}>Delete</button>
          <button type="submit" className={styles.saveButton}>Save</button>
        </div>
      </form>
    </div>
  );
}
