import styles from './properties.module.scss';
import Banner from '@/components/common/banner/banner';
import Filter from '@/components/common/filter/filter';
import AdvertCard from '@/components/common/advert-card/advert-card';

export default function PropertiesPage() {

  const adverts = [
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
  ];

  return (
    <div className={styles.mainContainer}>
      <Banner title="PROPERTIES" />

      <div className={styles.content}>
        <div className={styles.filtersContainer}>
          {/* <Filter /> */}
        </div>

        <div className={styles.advertsContainer}>
          {adverts.map(({ id, title, city, district, price }) => (
            < AdvertCard key={id} {...{ title, city, district, price }} />
          ))}
        </div>
      </div>
    </div>
  )
}
