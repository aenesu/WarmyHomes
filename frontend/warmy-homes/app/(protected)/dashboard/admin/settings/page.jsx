import React from 'react'
import styles from './admin-settings.module.scss'
import Sidebar from '@/components/admin/sidebar/sidebar'

export default function AdminSettings() {
  return (
    <div>
               < Sidebar />
    <div className={styles.mainContainer}>
        
      <div className={styles.container}>
     
        <h4 className={styles.heading}>Reset Database</h4>
        <p className={styles.paragraph}>You are about to delete all records except those whose built-in fields are true. Are you sure to reset the database?</p>
        <button className={styles.button}>Reset Database</button>
      </div>
    </div>
    </div>
  )
}
