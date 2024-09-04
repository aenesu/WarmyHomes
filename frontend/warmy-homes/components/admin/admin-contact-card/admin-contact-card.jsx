import Link from "next/link";
import styles from "./admin-contact-card.module.scss"

export default function AdminContactMessagesCard({ name, email }) {
    return (
      <div className={styles.card}>
        <Link href={"/dashboard/admin/contact-messages/details/slug"} className={styles.link}> <div className={styles.name}>{name}</div> </Link>
        <div className={styles.email}>{email}</div>
        <div className={styles.action}>
          <button className={styles.deleteButton} style={{ cursor: 'not-allowed' }}>
            <img src="/assets/vectors/bin.svg" alt="Delete Message" />
          </button>
        </div>
      </div>
    );
  }