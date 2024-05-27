import styles from "./discover-popular-properties.module.scss";

export default function DiscoverPopularProperties() {
  return (
    <div className={styles.discoverPopularContainer}>
      <div className={styles.exploreTitle}>Discover Popular Properties</div>
      <div className={styles.exploreText}>Featured properties</div>
      <div className={styles.categoryCards}>
        <div className={styles.categoryCard}> </div>
        <div className={styles.categoryCard}> </div>
        <div className={styles.categoryCard}> </div>
        <div className={styles.categoryCard}> </div>
        <div className={styles.categoryCard}> </div>
        <div className={styles.categoryCard}> </div>
      </div>



    </div>
  )
}
