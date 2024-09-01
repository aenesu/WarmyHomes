import AdminContactMessagesCard from "@/components/admin/admin-contact-card/admin-contact-card";
import styles from "./admin-contact-messages.module.scss"

export default function AdminContactMessages() {
  return (
    <div className={styles.container}>
      <div className={styles.searchSection}>
        <input
          type="text"
          className={styles.searchInput}
          placeholder="Type something..."
        />
        <button className={styles.searchButton}>
          <img src="/assets/vectors/magnify.svg" alt="Search" />
        </button>
        <select className={styles.dropdown}>
          <option>All messages</option>
          <option>Read</option>
          <option>Unread</option>
        </select>
      </div>

      <div className={styles.unreadMessagesLabel}>
        Unread Messages
      </div>
      
      <div className={styles.header}>
        <h4>Name</h4>
        <h4>Email</h4>
        <h4>Actions</h4>
      </div>

      <div className={styles.messagesList}>
        <AdminContactMessagesCard name="Aaron,Hank" email="example@example.com" />
        <AdminContactMessagesCard name="Aaron, Hank" email="example@example.com" />
        <AdminContactMessagesCard name="Aaron, Hank" email="example@example.com" />
        <AdminContactMessagesCard name="Aaron, Hank" email="example@example.com" />
      </div>
    </div>
  );
}