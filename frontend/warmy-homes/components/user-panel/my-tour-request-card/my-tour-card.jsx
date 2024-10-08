"use client";

import Link from "next/link";
import styles from "./my-tour-card.module.scss";

export default function MyTourRequestCard({
  index,
  title,
  country_id,
  city_id,
  district_id,
  category_id,
  advert_type_id,
  status,
  price,
  time_id,
  guest_id,
  slug // Add slug as a prop
}) {
  // const router = useRouter(); // Initialize the router
  // const handleDelete = () => {
  //   // Navigate to the delete page using slug (or add custom logic)
  //   router.push(`/dashboard/manage/admin/tour-requests/details/${slug}`);
  // };

  // const handleEdit = () => {
  //   // Navigate to the edit page using slug
  //   router.push(`/dashboard/manage/admin/tour-requests/details/${slug}`);
  // };

  return (
    <div className={styles.card}>
      <div className={styles.container}>
        <Link href={"/properties/slug"} target="_blank">
          <img
            src="/assets/images/house-isolated-field 6.png"
            alt={title}
            className={styles.image}
          />
        </Link>
        <div>
          <Link href={"/properties/slug"} target="_blank" className={styles.link}> <div className={styles.title}>{title}</div> </Link>
          <div className={styles.location}>
            {district_id}, {city_id}, {country_id}
          </div>
          <div className={styles.price}>
            $ {price.toLocaleString("en-US", { minimumFractionDigits: 2, maximumFractionDigits: 2 })}
          </div>
        </div>
      </div>
      <div className={styles.category}>{category_id}</div>
      {guest_id && <div className={styles.category}>{guest_id}</div>}
      <div className={styles.status}>{status}</div>
      <div className={styles.type}>{advert_type_id}</div>
      {time_id && <div className={styles.time}>{time_id}</div>}

      <div className={styles.action}>
        <button className={styles.actButton} style={{ cursor: 'not-allowed' }} >
          <img src="/assets/vectors/bin.svg" alt="Rubbish Symbol" />
        </button>
        <button className={styles.editButton} >
          <Link href={"/profile/tour-requests/detail/slug"}> <img src="/assets/vectors/editP.svg" alt="Edit Symbol" /> </Link>
        </button>
      </div>
    </div>
  );
}
