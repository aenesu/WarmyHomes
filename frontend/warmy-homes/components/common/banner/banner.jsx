import React from 'react';
import styles from './banner.module.scss';

export default function Banner({ title }) {
  return (
    <div className={styles.mainContainer}>
      <div className={styles.container}>
        <div className={styles.banner}>
          <p>{title}</p>
        </div>
      </div>
    </div>
  );
}
