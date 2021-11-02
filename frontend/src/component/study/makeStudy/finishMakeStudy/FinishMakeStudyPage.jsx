import React, { PureComponent } from 'react';
import style from "../../../../css/study/study_make_complete/study_make_complete.module.css";
import FinishMakeStudyContents from './FinishMakeStudyContents';
import FinishMakeStudyHeadCount from './FinishMakeStudyHeadCount';
import FinishMakeStudyTag from "./FinishMakeStudyTag";
import FinishMakeStudyTitle from './FinishMakeStudyTitle';
import FinishMakeStudyWriter from './FinishMakeStudyWriter';

class FinishMakeStudyPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <main>
          <div className={style.main_wrap}>
            <div className={style.main__studyComplete}>
              <div className={style["main__studyComplete-wrap"]}>
                <FinishMakeStudyTitle />
                <FinishMakeStudyWriter />
                <FinishMakeStudyTag />
                <FinishMakeStudyContents />
                <FinishMakeStudyHeadCount />
              </div>
            </div>
          </div>
        </main>
      </React.Fragment>
    );
  }
}

export default FinishMakeStudyPage;


