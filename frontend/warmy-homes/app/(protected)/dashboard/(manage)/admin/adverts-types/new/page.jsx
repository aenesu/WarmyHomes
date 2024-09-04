import styles from "./adverts-types-new.module.scss"

export default function AdvertTypesNew() {
  return (
    <div className={styles.container}>
      <div className={styles.fieldGroup}>
        <label htmlFor="title" className={styles.label}>Title</label>
        <input
          type="text"
          id="title"
          className={styles.input}
        />
      </div>
      <div className={styles.buttons}>
        <button className={styles.createButton}>Create</button>
      </div>
    </div>
  );
}
