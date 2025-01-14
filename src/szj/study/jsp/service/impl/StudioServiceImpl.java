package szj.study.jsp.service.impl;

import szj.study.jsp.dao.IStudioDao;
import szj.study.jsp.dao.impl.StudioDaoImpl;
import szj.study.jsp.pojo.Studio;
import szj.study.jsp.service.IStudioService;
import java.util.List;

public class StudioServiceImpl implements IStudioService {
    private final IStudioDao studioDao = new StudioDaoImpl();

    @Override
    public List<Studio> getAllStudios() {
        return studioDao.selectAllStudios();
    }

    @Override
    public Studio getStudioById(int studioId) {
        return studioDao.selectStudioById(studioId);
    }

    @Override
    public void addStudio(Studio studio) {
        studioDao.insertStudio(studio);
    }

    @Override
    public void updateStudio(Studio studio) {
        studioDao.updateStudio(studio);
    }

    @Override
    public void deleteStudio(int studioId) {
        studioDao.deleteStudio(studioId);
    }

    @Override
    public List<Studio> getStudiosByPage(int pageNum, int pageSize, String searchKeyword) {
        return studioDao.selectStudiosByPage(pageNum, pageSize, searchKeyword);
    }

    @Override
    public int getTotalStudios(String searchKeyword) {
        return studioDao.getTotalStudios(searchKeyword);
    }
} 