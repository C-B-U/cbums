import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalReading/study_workJournalReading.module.css";
import ReadStudyActivityButton from "./ReadStudyActivityButton";
import image1 from "../../../images/default/join1.jpg";
import image2 from "../../../images/default/join2.jpg";
class ReadStudyActivityTable extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__reading-wrap"]}>
              <table className={style["main__reading-table"]}>
                <tbody>
                  <tr>
                    <td className={style.title}>제목</td>
                    <td>자바스터디 1주차</td>
                  </tr>
                  <tr>
                    <td className={style.title}>참여인원</td>
                    <td>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <div
                              className={style["main__reading-table-button"]}
                            >
                              결석
                            </div>
                            <input
                              className={style["main__reading-table-attend"]}
                              type="hidden"
                              name="mem1"
                              value="0"
                            />{" "}
                            {/* -value 결석:0, 지각:1, 출석:2--*/}
                            6기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <div
                              className={style["main__reading-table-button"]}
                            >
                              결석
                            </div>
                            <input
                              className={style["main__reading-table-attend"]}
                              type="hidden"
                              name="mem2"
                              value="0"
                            />
                            7기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <div
                              className={style["main__reading-table-button"]}
                            >
                              결석
                            </div>
                            <input
                              className={style["main__reading-table-attend"]}
                              type="hidden"
                              name="mem3"
                              value="0"
                            />
                            8기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <div
                              className={style["main__reading-table-button"]}
                            >
                              결석
                            </div>
                            <input
                              className={style["main__reading-table-attend"]}
                              type="hidden"
                              name="mem4"
                              value="0"
                            />
                            9기 김부엉
                          </label>
                        </li>
                      </ul>
                    </td>
                  </tr>
                  <tr>
                    <td className={style.title}>진행날짜</td>
                    <td>2021년 07월 18일</td>
                  </tr>
                  <tr>
                    <td className={style.title}>세부내용</td>
                    <td>
                      <div className={style["main__reading-table-tdBox"]}>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit.
                        Placeat, ut qui nemo repellendus, possimus quia
                        inventore odit vero corporis rerum officiis molestiae
                        dolores aliquam pariatur doloremque, aspernatur soluta
                        tempore magnam.
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td className={style.title}>활동사진</td>
                    <td>
                      <ul className={style["main__reading-image-ul"]}>
                        <li>
                          <img
                            src={image1}
                            alt="활동사진1"
                            className={style["main__reading-image"]}
                          />
                        </li>
                        <li>
                          <img
                            src={image2}
                            alt="활동사진1"
                            className={style["main__reading-image"]}
                          />
                        </li>
                      </ul>
                    </td>
                  </tr>
                  <tr>
                    <td className={style.title}>숙제여부</td>
                    <td>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <input
                              type="checkbox"
                              name="homework-checkbox"
                              value="Y"
                              checked
                              disabled
                            />{" "}
                            {/* value가 Y일때 checked 옵션 추가 */}
                            6기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <input
                              type="checkbox"
                              name="homework-checkbox"
                              value="Y"
                              checked
                              disabled
                            />
                            7기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <input
                              type="checkbox"
                              name="homework-checkbox"
                              value="Y"
                              checked
                              disabled
                            />
                            8기 김부엉
                          </label>
                        </li>
                      </ul>
                      <ul className={style["main__reading-table-checkbox-ul"]}>
                        <li>
                          <label
                            className={style["main__reading-table-checkbox"]}
                          >
                            <input
                              type="checkbox"
                              name="homework-checkbox"
                              value="N"
                              disabled
                            />
                            9기 김부엉
                          </label>
                        </li>
                      </ul>
                    </td>
                  </tr>
                </tbody>
              </table>
              <ReadStudyActivityButton />
            </div>
          </React.Fragment>
        );
    }
}

export default ReadStudyActivityTable;
