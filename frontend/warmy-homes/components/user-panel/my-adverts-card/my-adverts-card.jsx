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
      <div className={styles.container}>
        <img
          src="/assets/images/house-isolated-field 6.png"
          alt={title}
          className={styles.image}
        />
        <div>
          <div className={styles.title}>{title}</div>
          <div className={styles.city_id}>{city_id}</div>
          <div className={styles.location}>{location}</div>
          <div className={styles.price}>{price}</div>
        </div>
      </div>
      <div className={styles.datePublished}>{create_at}</div>
      <div className={styles.status}>{status}</div>
      <div className={styles.view}> <img src="/assets/vectors/view.svg" alt="View Icon" className={styles.viewIcon} /> {view_count}</div>
      <div className={styles.action}>
        <button className={styles.editButton}><img src="/assets/vectors/edit.svg" alt="Edit Symbol"></img></button> 
      </div>
    </div>
  );
}
