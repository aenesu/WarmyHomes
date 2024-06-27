import styles from './unauthorized.module.scss';

export default function Unauthorized() {
  return (
    <div className={styles.mainContainer}>
        <div className={styles.banner}>
          <p>UNAUTHORIZED</p>
        </div>
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
          <button className={styles.goHomePagebutton}>LOGOUT</button>
        </div>
      </div>
    </div>
  );
}
