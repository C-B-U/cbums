import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalWriting/WriteStudyActivity.module.css";
import WriteStudyActivityButton from "./WriteStudyActivityButton";
import styled from 'styled-components';
import WriteStudyActivityAttend from "./WriteStudyActivityAttend";

const Td = styled.td`
  border: 1px solid #444444;
  padding: 10px;
`;
const Input = styled.input`
  margin: 0.5em;
`;

class WriteStudyActivityTable extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__writing-wrap"]}>
              <form className={style["main__writing-form"]}>
                <table className={style["main__writing-table"]}>
                  <tbody>
                    <tr>
                      <Td className={style.tdTitle}>제목</Td>
                      <Td>
                        <Input
                          type="text"
                          name="study-title"
                          autocomplete="off"
                          style={{ widTd: "95%" }}
                        />
                      </Td>
                    </tr>
                    <tr>
                      <Td className={style.tdTitle}>참여인원</Td>
                      {/*팀원들 출석/결석/지각 여부*/}
                      <Td>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <WriteStudyActivityAttend name="mem1" />
                              6기 김부엉
                            </label>
                          </li>
                        </ul>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <WriteStudyActivityAttend name="mem2" />
                              7기 김부엉
                            </label>
                          </li>
                        </ul>
                        <ul
                          className={
                            style["mNamea{style[in__writing-table-checkbox-ul"]
                          }
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <WriteStudyActivityAttend name="mem3" />
                              8기 김부엉
                            </label>
                          </li>
                        </ul>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <WriteStudyActivityAttend name="mem4" />
                              9기 김부엉
                            </label>
                          </li>
                        </ul>
                      </Td>
                    </tr>
                    <tr>
                      <Td className={style.tdTitle}>진행날짜</Td>
                      <Td>
                        <Input type="date" name="study-duration" />
                      </Td>
                    </tr>
                    <tr>
                      <Td className={style.tdTitle}>세부내용</Td>
                      <Td>
                        <textarea
                          name="study-explanation"
                          style={{
                            resize: "none",
                            widTd: "95%",
                            height: "130px",
                          }}
                          required
                          wrap="hard"
                        />
                      </Td>
                    </tr>
                    <tr>
                      <Td className={style.tdTitle}>활동사진</Td>
                      <Td>
                        <Input
                          type="file"
                          name="study-image"
                          id="study-image"
                          multiple
                        />
                        <div id="image_container"></div>
                      </Td>
                    </tr>
                    <tr>
                      <Td className={style.tdTitle}>숙제여부</Td>
                      <Td>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          {/* 팀원들 숙제 여부*/}
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <Input type="checkbox" name="homework-checkbox" />
                              9기 김김김
                            </label>
                          </li>
                        </ul>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <Input type="checkbox" name="homework-checkbox" />
                              5기 김김김
                            </label>
                          </li>
                        </ul>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <Input type="checkbox" name="homework-checkbox" />
                            </label>
                          </li>
                        </ul>
                        <ul
                          className={style["main__writing-table-checkbox-ul"]}
                        >
                          <li>
                            <label
                              className={style["main__writing-table-checkbox"]}
                            >
                              <Input type="checkbox" name="homework-checkbox" />
                            </label>
                          </li>
                        </ul>
                      </Td>
                    </tr>
                  </tbody>
                </table>
                <WriteStudyActivityButton />
              </form>
            </div>
          </React.Fragment>
        );
    }
}

export default WriteStudyActivityTable;
