import styles from './not-found.module.scss';

export default function NotFound() {
  return (
    <div className={styles.mainContainer}>
        <div className={styles.banner}>
          <p>NOT FOUND</p>
        </div>
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
          <button className={styles.goHomePagebutton}>Go to Home Page</button>
        </div>
      </div>
    </div>
  );
}
