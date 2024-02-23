package backend.jobportal.service.impl;

import backend.jobportal.entity.Job;
import backend.jobportal.payload.JobDto;
import backend.jobportal.repository.JobRepository;
import backend.jobportal.service.JobService;
import backend.jobportal.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createJob(JobDto jobDto) throws IOException {

        MultipartFile companyLogo = jobDto.getCompanyLogo();


        byte[] content = companyLogo.getBytes();
        Path fullPath = Paths.get(AppConstant.PathToSaveLogo + companyLogo.getOriginalFilename());

        //saveLogo(content, fullPath);

        companyLogo.transferTo(fullPath);


        Job job = mapToJob(jobDto);
        job.setLogoPath(fullPath.toString());


        Job savedJob = jobRepository.save(job);

        return "Job Created Successfully";
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }


    private void saveLogo(byte[] bytes, String fullPath) throws IOException {

        Path path = Path.of(fullPath);
        Files.write(path, bytes);

    }


    private Job mapToJob(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        return job;
    }

}
