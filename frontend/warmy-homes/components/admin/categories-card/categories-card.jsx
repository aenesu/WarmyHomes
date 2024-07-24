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
          <div className={styles.container}>
            <img
              src="/assets/vectors/house.svg"
              alt="House Icon"
              className={styles.icon} 
            />
            <div className={styles.details}>
              <div className={styles.name}>{name}</div>
              <div className={styles.sequence}>{sequence}</div>
            </div>
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
        </div>
      );
}
