"use client";
import { Suspense, useState } from 'react';
import { useSearchParams } from 'next/navigation';
import styles from './properties.module.scss';
import Banner from '@/components/common/banner/banner';
import AdvertCard from '@/components/common/advert-card/advert-card';
import Filter from '@/components/common/filter/filter';

export default function PropertiesPage() {

  const searchParams = useSearchParams();
  const filterParams = {
    initialQuery: searchParams.get('q') || '',
    initialAdvertTypeId: searchParams.get('advert_type_id') || '',
    initialCategoryId: searchParams.getAll('category_id') || [],
    initialMin: searchParams.get('min') || '',
    initialMax: searchParams.get('max') || '',
    initialLocation: searchParams.get('location') || '',
  };

  // Sample adverts data
  const adverts = [
    { id: 1, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 2, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 3, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 4, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 5, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 6, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 7, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 8, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 9, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 10, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 11, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 12, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },
    { id: 13, title: "Luxury villa in Central Park", city: "Ankara", district: "Balgat", price: "1400,00" },

  ];

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 10; // Number of items per page
  const totalPages = Math.ceil(adverts.length / itemsPerPage);
  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedAdverts = adverts.slice(startIndex, startIndex + itemsPerPage);

  // Page change handler
  const handlePageChange = (direction) => {
    setCurrentPage((prevPage) => prevPage + direction);
  };

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <div className={styles.mainContainer}>
        <Banner title="PROPERTIES" />

        <div className={styles.content}>
          <div className={styles.filtersContainer}>
            <Filter filterParams={filterParams} />
          </div>

          <div className={styles.advertsContainer}>
            {paginatedAdverts.map(({ id, title, city, district, price }) => (
              <AdvertCard key={id} {...{ title, city, district, price }} />
            ))}
          </div>
        </div>
        {/* Pagination controls */}
        <div className={styles.pagination}>
          <button
            onClick={() => handlePageChange(-1)}
            disabled={currentPage === 1}
            className={`${styles.pageButton} ${currentPage === 1 ? styles.disabled : ''}`}
          >
            <img src="/assets/vectors/arrowL.svg" alt="Previous" />
          </button>
          <span className={styles.pageInfo}>
            {currentPage} / {totalPages}
          </span>
          <button
            onClick={() => handlePageChange(1)}
            disabled={currentPage === totalPages}
            className={`${styles.pageButton} ${currentPage === totalPages ? styles.disabled : ''}`}
          >
            <img src="/assets/vectors/arrow.svg" alt="Next" />
          </button>
        </div>
      </div>
    </Suspense>
  );
}
