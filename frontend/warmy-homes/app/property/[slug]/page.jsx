import styles from './properity.module.scss';

export default function RandomProp() {

  const advert = {
    id: 'home',
    title: 'ev',
    desc: '',
    slug: '',
    price: '',
    status: '',
    built_in: '',
    is_active: '',
    view_count: '',
    location: '',
    advert_type_id: '',
    country_id: '',
    city_id: '',
    district_id: '',
    user_id: '',
    category_id: '',
    create_at: '',
    update_at: '',
  }

  return (
    <div className={styles.container}>

      <div className={styles.banner}>
        <div className={styles.title}>
          {advert.title}
        </div>
      </div>

      <div className={styles.content}>
        <div className={styles.left}>
          <div className={styles.photoContainer}>
            fotoğraflar
          </div>

          <div className={styles.detailsContainer}>
          </div>

          <div className={styles.locationContainer}>
          </div>
        </div>

        <div className={styles.right}>
          <div className={styles.tourContainer}>
            places
          </div>
        </div>
      </div>

    </div>
  )
}
