import React, { PureComponent } from "react";
import { FaSearch } from "react-icons/fa";
import style from "../../../css/study/study_workJournal/study_work.module.css";

class StudyNavTitle extends PureComponent {
  render() {
    const { studytitle } = this.props;
    return (
      <React.Fragment>
        <div className={style["main__title-wrap"]}>
          <div className={style["main__title"]}>
            <h2>{studytitle}</h2>
          </div>
          <div className={style["main__search-wrap"]}>
            <div className={style["main__search"]}>
              <table width="200">
                <tr>
                  <td>
                    <select name="keyField">
                      <option value="subject">제목</option>
                      <option value="writer">작성자명</option>
                    </select>
                  </td>
                  <td>
                    <input
                      className={style.search}
                      type="search"
                      name="search"
                    />
                  </td>
                  <td>
                    <button
                      className={style["main__serach-button"]}
                      type="submit"
                      name="click"
                      value=""
                    >
                      <FaSearch
                        style={{ color: "#c1c1c1", fontSize: "1.1em" }}
                      />
                    </button>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default StudyNavTitle;

