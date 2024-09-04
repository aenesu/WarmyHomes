import styles from "./edit-adverts-types.module.scss";

export default function EditAdvertType() {
    return (
      <div className={styles.container}>
        <div className={styles.formGroup}>
          <label htmlFor="title">Title</label>
          <input
            type="text"
            id="title"
            name="title"
            placeholder="Title"
            value="For Sale"
            className={styles.input}
          />
        </div>
  
       
        <div className={styles.buttonGroup}>
          <button className={styles.deleteButton}>Delete</button>
          <button className={styles.saveButton}>Save</button>
        </div>
      </div>
    );
  }