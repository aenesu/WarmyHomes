import styles from "./new-advert.module.scss";
import Banner from "@/components/common/banner/banner";
import TextInput from "@/components/common/text-input/text-input";
import TextArea from "@/components/common/text-area/text-area";

export default function NewAdvert() {

  return (
    <div className={styles.mainContainer}>
      <Banner title="NEW ADVERT" />

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
              {/* Country Dropdown */}
              <div className={styles.dropdown}>
                <label htmlFor="country">Country</label>
                <select id="country" name="country" className={styles.dropdownSelect}>
                  <option value="Turkey">Turkey</option>
                  <option value="Canada">Canada</option>
                  <option value="United States">United States</option>
                </select>
              </div>
              {/* City Dropdown */}
              <div className={styles.dropdown}>
                <label htmlFor="city">City</label>
                <select id="city" name="city" className={styles.dropdownSelect}>
                  <option value="Ankara">Ankara</option>
                  <option value="Vancouver">Vancouver</option>
                  <option value="Melbourne">Melbourne</option>
                </select>
              </div>
              {/* Neighborhood Dropdown */}
              <div className={styles.dropdown}>
                <label htmlFor="neighborhood">Neighborhood</label>
                <select id="neighborhood" name="neighborhood" className={styles.dropdownSelect}>
                  <option value="Option 1">Option 1</option>
                  <option value="Option 2">Option 2</option>
                  <option value="Option 3">Option 3</option>
                </select>
              </div>
            </div>
            <TextInput label="location" />
          </div>

          <div className={styles.propertiesBox}>
            <h2>Properties</h2>
            <div className={styles.formInputs}>
              {/* Category Dropdown */}
              <div className={styles.dropdown}>
                <label htmlFor="category">Category</label>
                <select id="category" name="category" className={styles.dropdownSelect}>
                  <option value="House">House</option>
                  <option value="Apartment">Apartment</option>
                  <option value="Villa">Villa</option>
                  <option value="Office">Office</option>
                </select>
              </div>
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

          <h2>Images</h2>
          <div className={`${styles.formInputs} ${styles.photoAdd}`}>
            <div className={styles.plusSign}>+</div>
          </div>

          <button className={styles.submitButton}>Create</button>
        </form>
      </div>
    </div>
  );
}