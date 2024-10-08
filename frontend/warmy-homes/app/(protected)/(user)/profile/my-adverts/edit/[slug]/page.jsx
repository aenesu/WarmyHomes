import styles from "./edit-advert.module.scss";
import Banner from "@/components/common/banner/banner";
import TextInput from "@/components/common/text-input/text-input";
import TextArea from "@/components/common/text-area/text-area";

export default function NewAdvert() {

  return (
    <div className={styles.mainContainer}>
      <Banner title="EDIT ADVERT" />

      <div className={styles.container}>
        <form action="" method="POST">
          <h2>Common Information</h2>
          <div className={styles.formInputs}>
            <TextInput label="title" />
            <TextArea label="description" height="220px" />
            <div className={styles.multipleInput}>
              <TextInput label="price" width="395px" />
              <TextInput label="advert-type" width="395px" />
            </div>
          </div>

          <h2>Address Information</h2>
          <div className={styles.formInputs}>
            <div className={styles.multipleInput}>
              <TextInput label="country" value={"Choose"} width="257px" />
              <TextInput label="city" value={"Choose"} width="257px" />
              <TextInput label="neigbourhood" value={"Choose"} width="257px" />
            </div>
            <TextInput label="location" />
          </div>

          <div className={styles.propertiesBox}>
            <h2>Properties</h2>
            <div className={styles.formInputs}>
              <TextInput label="category" value={"Choose"} width="548px" />
              <div className={styles.multipleInput}>
                <TextInput label="floor" width="257px" />
                <TextInput label="bedroom" width="257px" />
                <TextInput label="bathroom" width="257px" />
              </div>
              <div className={styles.multipleInput}>
                <TextInput label="garage" width="257px" />
                <TextInput label="year-of-built" width="257px" />
                <TextInput label="size" width="257px" />
              </div>
            </div>
          </div>

          <button className={styles.submitButton}>Update</button>


          <h2>Images</h2>
          <div className={`${styles.formInputs} ${styles.photoAdd}`}>
            <div className={styles.plusSign}>+</div>
            <button className={styles.uploadButton}>
              UPLOAD
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}
