import styles from "./categories-card.module.scss"

export default function CategoriesCard({
    index,
    icon,
    name,
    sequence,
    active
    
})

{
  return (
    <div className={styles.card}>
      <div className={styles.icon}>
        <img src={`/assets/vectors/${icon}`} alt="Category Icon" />
      </div>
      <div className={styles.name}>{name}</div>
      <div className={styles.sequence}>{sequence}</div>
      <div className={styles.active}>
        {active ? (
          <img src="/assets/vectors/check-mark.svg" alt="Active" />
        ) : (
          <img src="/assets/vectors/cross.svg" alt="Inactive" />
        )}
      </div>
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