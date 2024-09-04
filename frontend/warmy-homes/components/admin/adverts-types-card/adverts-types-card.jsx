import Link from "next/link";
import styles from "./adverts-types-card.module.scss";

export default function AdvertTypesCard({ title }) {
  return (
    <div className={styles.card}>
      <div className={styles.title}>{title}</div>
      <div className={styles.action}>
        <button className={styles.editButton} style={{ cursor: 'not-allowed' }}>
          <img src="/assets/vectors/bin.svg" alt="Delete Symbol" />
        </button>
        <button className={styles.editButton}>
          <Link href="/dashboard/admin/adverts-types/edit/slug"> <img src="/assets/vectors/purpedit.svg" alt="Edit Symbol" /> </Link>
        </button>
      </div>
    </div>
  );
}
