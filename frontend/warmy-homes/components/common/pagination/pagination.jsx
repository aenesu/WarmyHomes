import React from 'react';
import styles from './pagination.module.scss';

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  return (
    <div className={styles.pagination}>
      {/* Outer arrow - Go to the first page (Double Left Arrow) */}
      <button
        className={`${styles.pageButton} ${currentPage === 1 ? styles.disabled : ''}`}
        onClick={() => onPageChange(1)}
        disabled={currentPage === 1}
      >
        &#xab; {/* Unicode for Double Left Arrow */}
      </button>

      {/* Inner arrow - Go back one page (Left Arrow) */}
      <button
        className={`${styles.pageButton} ${currentPage === 1 ? styles.disabled : ''}`}
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 1}
      >
        &#x2190; {/* Unicode for Left Arrow */}
      </button>

      {/* Page info */}
      <span className={styles.pageInfo}>
        {currentPage} / {totalPages}
      </span>

      {/* Inner arrow - Go forward one page (Right Arrow) */}
      <button
        className={`${styles.pageButton} ${currentPage === totalPages ? styles.disabled : ''}`}
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage === totalPages}
      >
        &#x2192; {/* Unicode for Right Arrow */}
      </button>

      {/* Outer arrow - Go to the last page (Double Right Arrow) */}
      <button
        className={`${styles.pageButton} ${currentPage === totalPages ? styles.disabled : ''}`}
        onClick={() => onPageChange(totalPages)}
        disabled={currentPage === totalPages}
      >
        &#xbb; {/* Unicode for Double Right Arrow */}
      </button>
    </div>
  );
};

export default Pagination;
