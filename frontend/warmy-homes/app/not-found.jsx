import styles from './not-found.module.scss';
import Link from 'next/link';
import Banner from '@/components/common/banner/banner';

export default function NotFound() {
  return (
    <div className={styles.mainContainer}>
         <Banner title="NOT FOUND" />
        <div className={styles.imageAndTextContainer}>
          <div className={styles.imageContainer}>
            <img className={styles.logo} src="/assets/images/not-found.jpeg" alt="Not Found" />
          </div>
          <div className={styles.textContainer}>
            <div className={styles.title}>
              Oops! It looks like you're lost.
            </div>
            <div className={styles.subtitle}>
              The page you&apos;re looking for isn&apos;t available. Try to search again or the use go to:
          </div>
          <Link href="/">
      <button className={styles.goHomePagebutton}>
        Go to Home Page
      </button>
    </Link>
        </div>
      </div>
    </div>
  );
}
