import styles from "./admin-contact-messages-details.module.scss";

export default function ContactMessageDetails() {
  return (
    <div className={styles.container}>
      <div className={styles.detailsSection}>
        <div className={styles.detailRow}>
          <span className={styles.label}>Name</span>
          <span className={styles.value}>John Doe</span>
        </div>
        <div className={styles.detailRow}>
          <span className={styles.label}>Email</span>
          <span className={styles.value}>john@doe.com</span>
        </div>
        <div className={styles.detailRow}>
          <span className={styles.label}>Message</span>
          <p className={styles.message}>
            Contrary to popular belief, Lorem Ipsum is not simply random text.
            It has roots in a piece of classical Latin literature from 45 BC,
            making it over 2000 years old. Richard McClintock, a Latin professor
            at Hampden-Sydney College in Virginia, looked up one of the more
            obscure Latin words, consectetur, from a Lorem Ipsum passage, and
            going through the cites of the word in classical literature,
            discovered the undoubtable source.
          </p>
        </div>
      </div>
      <div className={styles.actions}>
        <button className={styles.returnButton}>Return</button>
        <button className={styles.deleteButton}>Delete</button>
      </div>
    </div>
  );
}
