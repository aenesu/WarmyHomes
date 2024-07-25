import styles from "./adverts-types-card.module.scss";

export default function AdvertTypesCard({ title }) {
  return (
    <div className={styles.card}>
      <div className={styles.title}>{title}</div>
      <div className={styles.action}>
        <button className={styles.editButton}>
          <img src="/assets/vectors/bin.svg" alt="Delete Symbol" />
        </button>
        <button className={styles.editButton}>
          <img src="/assets/vectors/purpedit.svg" alt="Edit Symbol" />
        </button>
      </div>
    </div>
  );
}
