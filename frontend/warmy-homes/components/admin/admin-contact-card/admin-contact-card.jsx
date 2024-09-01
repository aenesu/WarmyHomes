import styles from "./admin-contact-card.module.scss"

export default function AdminContactMessagesCard({ name, email }) {
    return (
      <div className={styles.card}>
        <div className={styles.name}>{name}</div>
        <div className={styles.email}>{email}</div>
        <div className={styles.action}>
          <button className={styles.deleteButton}>
            <img src="/assets/vectors/bin.svg" alt="Delete Message" />
          </button>
        </div>
      </div>
    );
  }