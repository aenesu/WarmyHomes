import styles from "./my-adverts-card.module.scss";

export default function MyAdvertsCard({
  index,
  title,
  city_id,
  location,
  price,
  create_at,
  status,
  view_count,
}) {
  return (
    <div className={styles.card}>
      <img
        src="/assets/images/house-isolated-field 6.png"
        alt={title}
        className={styles.image}
      />
      <div className={styles.text}>
        <div>
          <div className={styles.title}>{title}</div>
          <div className={styles.city_id}>{city_id}</div>
          <div className={styles.location}>{location}</div>
          <div className={styles.price}>{price}</div>
        </div>
        <div className={styles.datePublished}>{create_at}</div>
        <div className={styles.status}>{status}</div>
        <div className={styles.details}>
          <div className={styles.stats}>
            <span className={styles.view}>👁️ {view_count}</span>
          </div>
        </div>
        <div className={styles.action}>
          <button className={styles.editButton}>✏️</button>
        </div>
      </div>
    </div>
  );
}
