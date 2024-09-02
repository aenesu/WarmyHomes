import styles from "./admin-users-card.module.scss";

export default function UsersCard({
    index,
    name, 
    email,
    phone
})

{
    return (
      <div className={styles.card}>
        <div className={styles.name}>{name}</div>
        <div className={styles.email}>{email}</div>
        <div className={styles.phone}>{phone}</div>
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