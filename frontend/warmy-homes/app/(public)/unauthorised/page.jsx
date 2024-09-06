import Link from 'next/link';
import styles from './unauthorized.module.scss';
import Banner from '@/components/common/banner/banner';

export default function Unauthorized() {
  return (
    <div className={styles.mainContainer}>
        <Banner title="UNAUTHORIZED" />
        <div className={styles.imageAndTextContainer}>
          <div className={styles.imageContainer}>
            <img className={styles.logo} src="/assets/images/403.jpeg" alt="Unauthorized" />
          </div>
          <div className={styles.textContainer}>
            <div className={styles.title}>
              Sorry you are not authorized to access.
            </div>
            <div className={styles.subtitle}>
              Please check your login credentials or contact the administrator.
          </div>
          <Link href={"/"} > <button className={styles.logoutButton}>LOGOUT</button> </Link>
        </div>
      </div>
    </div>
  );
}
