"use client";
import { useState } from 'react';
import styles from './new.module.scss';

export default function CategoryEdit() {
  const [status, setStatus] = useState('Active');

  const toggleStatus = () => {
    setStatus(prevStatus => (prevStatus === 'Active' ? 'Deactive' : 'Active'));
  };

  return (
    <div className={styles.container}>
      <form className={styles.form}>
        <div className={styles.sides}>
          <div className={styles.side}>

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
              <button type="submit" className={styles.saveButton}>Create</button>
            </div>
          </div>

          <div className={styles.side}>
            {/* Property Container */}
            <div className={styles.propertyContainer}>
              <h3 className={styles.propertyTitle}>Properties</h3>
              <div className={styles.addPropertyBox}></div>
              <div className={styles.propertyItem}>
              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}
