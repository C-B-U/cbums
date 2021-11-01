import React, { PureComponent } from 'react';
import style from "../../../../css/study/study_make_complete/study_make_complete.module.css";

class OpenedStudyWriter extends PureComponent {
    render() {
        return (
            <React.Fragment>
                <div className={style["main__studyComplete-writer-wrap"]}>
                    <div className={style["main__studyComplete-writer"]}>김부엉</div>  {/*--작성자 이름--*/}
                </div>
            </React.Fragment>
        );
    }
}

export default OpenedStudyWriter;