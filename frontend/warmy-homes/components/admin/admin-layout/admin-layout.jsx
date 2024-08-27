import Sidebar from '../sidebar/sidebar';
import styles from './admin-layout.module.scss'; // Ensure this file exists with appropriate styles

export default function AdminLayout({ children }) {
  return (
    <div className={styles.container}>
      <Sidebar />
      <div className={styles.mainContent}>
        {children}
      </div>
    </div>
  );
}